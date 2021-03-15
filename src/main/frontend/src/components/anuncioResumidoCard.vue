<template>
  <f7-card class="demo-card-header-pic">
    <f7-card-header
        class="no-border"
        valign="bottom"
        :style="headerStyle"
    >
      <f7-icon :f7="iconoEspecie"></f7-icon>
      {{ anuncioResumido.nombreMascota }}
      <f7-icon :f7="iconoTipo"></f7-icon>
    </f7-card-header
    >
    <f7-card-content>
      <strong>{{ anuncioResumido.especie? anuncioResumido.especie.nombre: '?' }} {{ anuncioResumido.tipo }}</strong>
      <p class="fecha">{{ fechaCreacionFormateado }}</p>
      <p>{{ anuncioResumido.raza? anuncioResumido.raza.nombre : '?' }}, {{ anuncioResumido.franjaEtaria? anuncioResumido.franjaEtaria.nombre : '?' }}</p>
      <p>{{ anuncioResumido.localidad? anuncioResumido.localidad.nombre : '?' }}</p>
    </f7-card-content>
  </f7-card>
</template>

<script>
import moment from 'moment';

export default {
  props: {
    anuncioResumido: Object
  },
  computed: {
    fechaCreacionFormateado() {
      return moment(this.anuncioResumido.fechaCreacion).calendar()
    },
    headerStyle() {
      if (this.anuncioResumido.fotoPrincipal) {
        return {
          "--background-image": "url(" + this.anuncioResumido.fotoPrincipal.url + ")"
        }
      }
    },
    iconoEspecie() {
      if (this.anuncioResumido.especie) {
        if (this.anuncioResumido.especie.id == 1) {
          return 'dog'
        }
        if (this.anuncioResumido.especie.id == 2) {
          return 'cat'
        }
      }
      return ''
    },
    iconoTipo() {
      if (this.anuncioResumido.tipo === 'PERDIDO') {
        return 'question_circle'
      }
      if (this.anuncioResumido.tipo === 'ENCONTRADO') {
        return 'scope'
      }
      return ''
    }
  }
}
</script>

<style scoped>
.demo-card-header-pic .card-header {
  height: 140px;
  background-size: cover;
  background-position: center;
  color: #fff;
  text-shadow: 0 0 3px #000;
}

.card-header {
  background-image: var(--background-image);
}

.card-content p {
  margin: 0 5px;
}

.fecha {
  font-size: 0.9em;
  opacity: 0.7;
}
</style>