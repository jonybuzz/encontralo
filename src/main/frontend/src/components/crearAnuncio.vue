<template>

  <div>
    <f7-row class="enc-col-center-content">
      <f7-col width="90" xsmall="90" small="80" medium="70" xlarge="50" class="enc-main-button">
        <p><small><i>* Campos requeridos</i></small></p>
        <f7-list inline-labels no-hairlines-md>
          <f7-list-item
              radio radio-icon="start"
              title="Perro"
              name="especieId"
              value="1"
              :checked="especieId === 1"
              @change="especieId = parseInt($event.target.value)"
              checked></f7-list-item>
          <f7-list-item
              radio radio-icon="start"
              title="Gato"
              name="especieId"
              value="2"
              :checked="especieId === 2"
              @change="especieId = parseInt($event.target.value)"
          ></f7-list-item>

          <f7-list-item-row>
            <f7-list-item-cell>
              <f7-row class="enc-col-center-content">
                <f7-col>
                  <selector-fotos v-on:fotoCargada="agregarFotoAlAnuncio"></selector-fotos>
                </f7-col>
              </f7-row>
            </f7-list-item-cell>
          </f7-list-item-row>

          <f7-list-input
              label="Sexo*"
              type="select"
              placeholder="Seleccionar..."
              v-model:value="sexo"
          >
            <option value="" selected disabled>Seleccionar...</option>
            <option value="MACHO">Macho</option>
            <option value="HEMBRA">Hembra</option>
          </f7-list-input>

          <f7-list-item
              title="Raza*"
              smart-select
              :smart-select-params="{searchbar: {removeDiacritics: true}, searchbarPlaceholder: 'Buscar...', closeOnSelect: true, virtualList: true}">
            <select name="razaId" v-model="razaId">
              <option value="0" selected disabled></option>
              <option v-for="raza in razas" :key="raza.id" :value="raza.id">
                {{ raza.nombre }}
              </option>
            </select>
          </f7-list-item>

          <f7-list-input
              label="Tamaño*"
              type="select"
              placeholder="Seleccionar..."
              v-model:value="tamanioId"
          >
            <option value="0" selected disabled>Seleccionar...</option>
            <option v-for="tamanio in tamanios" :key="tamanio.id" :value="tamanio.id">{{ tamanio.nombre }}</option>
          </f7-list-input>

          <f7-list-input
              label="Edad*"
              type="select"
              placeholder="Seleccionar..."
              v-model:value="franjaEtariaId"
          >
            <option value="0" selected disabled>Seleccionar...</option>
            <option v-for="franjaEtaria in seleccionables.franjasEtarias" :key="franjaEtaria.id"
                    :value="franjaEtaria.id">{{ franjaEtaria.nombre }}, {{ franjaEtaria.aclaracion }}
            </option>
          </f7-list-input>

          <f7-list-item title="Colores" smart-select>
            <select name="coloresIds" multiple v-model="coloresIds">
              <option value="0" selected disabled></option>
              <option v-for="color in seleccionables.colores" :key="color.id" :value="color.id">
                {{ color.nombre }}
              </option>
            </select>
          </f7-list-item>

          <f7-list-input
              label="Pelaje*"
              type="select"
              v-model:value="pelajeId"
          >
            <option value="0" selected disabled>Seleccionar...</option>
            <option v-for="pelaje in seleccionables.pelajes" :key="pelaje.id" :value="pelaje.id">{{ pelaje.nombre }}
            </option>
          </f7-list-input>

          <f7-list-input
              label="¿Tiene collar?"
              type="select"
              v-model:value="tieneCollar"
          >
            <option selected value="false">No</option>
            <option value="true">Sí</option>
          </f7-list-input>

          <f7-list-item
              title="Localidad*"
              smart-select
              :smart-select-params="{searchbar: {removeDiacritics: true}, searchbarPlaceholder: 'Buscar...', closeOnSelect: true, virtualList: true}">
            <select name="localidadId" v-model="localidadId">
              <option value="0" selected disabled></option>
              <option v-for="localidad in seleccionables.localidades" :key="localidad.id" :value="localidad.id">
                {{ localidad.nombre }}, {{ localidad.provincia }}
              </option>
            </select>
          </f7-list-item>

          <f7-list-input
              label="Tel. de contacto*"
              type="text"
              placeholder=""
              v-model:value="telefonoContacto"
          ></f7-list-input>

          <f7-list-input
              label="Nombre mascota"
              type="text"
              placeholder=""
              v-model:value="nombreMascota"
          ></f7-list-input>

          <f7-list-input
              label="Comentarios"
              type="textarea"
              placeholder=""
              v-model:value="comentario"
          ></f7-list-input>

        </f7-list>
      </f7-col>
    </f7-row>

    <f7-row class="enc-col-center-content">
      <f7-col width="90" xsmall="90" small="60" medium="33" xlarge="20" class="enc-main-button">
        <f7-button large fill raised href="/" @click="crearAnuncio" :disabled="faltaCompletar">Anunciar</f7-button>
      </f7-col>
    </f7-row>
  </div>

</template>

<script>
import {f7, useStore} from 'framework7-vue'
import requestsAnuncio from './../js/requests/anuncio'
import SelectorFotos from './selectorFotos'

export default {
  name: "crearAnuncio.vue",
  props: {
    tipoAnuncio: String
  },
  components: {
    SelectorFotos
  },
  setup() {
    const seleccionables = useStore('seleccionables');
    return {
      seleccionables
    }
  },
  data() {
    return {
      tipo: this.tipoAnuncio,
      especieId: 1,
      razaId: 0,
      tamanioId: 0,
      sexo: '',
      franjaEtariaId: 0,
      coloresIds: [0],
      tieneCollar: false,
      pelajeId: 0,
      localidadId: 0,
      telefonoContacto: '',
      nombreMascota: '',
      comentario: '',
      fotos: []
    };
  },
  computed: {
    seleccionablesSegunEspecie() {
      let self = this;
      return self.seleccionables.especies
          .find(e => e.id === self.especieId)
    },
    razas() {
      return this.seleccionablesSegunEspecie.razas
    },
    tamanios() {
      return this.seleccionablesSegunEspecie.tamanios
    },
    faltaCompletar() {
      return !(this.razaId && this.tamanioId && this.sexo && this.franjaEtariaId && this.pelajeId && this.localidadId && this.telefonoContacto)
    },
  },
  watch: {
    especieId() {
      this.razaId = 0
      this.tamanioId = 0
    }
  },
  methods: {
    crearAnuncio() {
      const self = this
      const nuevoAnuncio = {
        tipo: this.tipoAnuncio,
        nombreMascota: orNull(self.nombreMascota),
        especieId: orNull(self.especieId),
        razaId: orNull(self.razaId),
        tamanioId: orNull(self.tamanioId),
        sexo: orNull(self.sexo),
        franjaEtariaId: orNull(self.franjaEtariaId),
        coloresIds: cleanArray(self.coloresIds),
        tieneCollar: self.tieneCollar,
        pelajeId: orNull(self.pelajeId),
        localidadId: orNull(self.localidadId),
        comentario: orNull(self.comentario),
        telefonoContacto: orNull(self.telefonoContacto),
        fotos: self.fotos.map((val, i) => {
          return {posicion: i + 1, datosBase64: val}
        })
      }
      console.dir(nuevoAnuncio)
      requestsAnuncio.crear(nuevoAnuncio)
          .then((response) => {
            console.log(response.data)
            f7.toast.create({
              position: "top",
              horizontalPosition: "right",
              icon: '<i class="f7-icons">captions_bubble</i>',
              text: `¡Anuncio #${response.data} publicado!`,
              closeTimeout: 3000
            }).open();
          })

      function orNull(value) {
        if (typeof value == 'number') {
          return value ? value : null
        }
        if (typeof value == 'string') {
          return value.trim() ? value.trim() : null
        }
      }

      function cleanArray(arr) {
        arr.splice(arr.indexOf("0"), 1)
        return arr
      }
    },
    agregarFotoAlAnuncio(file) {
      this.fotos.push(file.contents.split(',')[1])
    }
  }
}

</script>

<style scoped>
</style>