<template>
  <form action="/" class="dropzone"></form>
</template>

<script>
import {onMounted} from 'vue'
import Dropzone from 'dropzone'

export default {
  name: "selectorFotos.vue",
  setup(_, ctx) {
    onMounted(() => {
      Dropzone.autoDiscover = false;
      let myDropzone = new Dropzone(".dropzone", {
        dictDefaultMessage: "Agregar fotos...",
        maxFiles: 3,
        maxFilesize: 4, //MB
        acceptedFiles: 'image/jpg,image/jpeg',
        addRemoveLinks: true,
        dictRemoveFile: 'Eliminar',
        dictMaxFilesExceeded: 'Se aceptan hasta {{maxFiles}} fotos',
        dictCancelUpload: 'Cancelar',
        dictInvalidFileType: 'El tipo de archivo no es aceptado',
        dictFileTooBig: 'La foto es muy pesada ({{filesize}}MB). Máximo: {{maxFilesize}}MB.',
        accept: localAcceptHandler
      });

      myDropzone.localSuccess = function (file, done) {
        ctx.emit('fotoCargada', file)
        console.log(`Cargado: ${file.name}`)
        done()
      }

      function localAcceptHandler(file, done) {
        this._sendIntercept(file).then(result => {
          file.contents = result;
          if (typeof this.localSuccess === 'function') {
            this.localSuccess(file, done);
          } else {
            done(); // empty done signals success
          }
        }).catch(result => {
          if (typeof this.localFailure === 'function') {
            file.contents = result;
            this.localFailure(file, done);
          } else {
            done(`Error al cargar foto ${file.name}`);
            console.error(result)
          }
        });
      }

      myDropzone.submitRequest = function (xhr, formData, files) {
        this._finished(files, 'locally resolved, refer to "contents" property');
      };

      myDropzone._sendIntercept = function (file, options = {}) {
        return new Promise((resolve, reject) => {
          if (!options.readType) {
            const mime = file.type;
            const _textTypes = ['text/*', 'application/xml', 'application/x-sh', 'application/x-script', 'image/svg+xml'];
            const textType = _textTypes.find(type => {
              const regexp = new RegExp(type);
              return regexp.test(mime);
            });
            options.readType = textType ? 'readAsText' : 'readAsDataURL';
          }
          let reader = new window.FileReader();
          reader.onload = () => {
            resolve(reader.result);
          };
          reader.onerror = () => {
            reject(reader.result);
          };
          // run the reader
          reader[options.readType](file);
        });
      }
    })
  }
}
</script>

<style scoped>
@import '../../node_modules/dropzone/dist/dropzone.css';

.dropzone {
  border-width: 1px;
}
</style>