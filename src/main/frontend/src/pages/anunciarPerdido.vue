<template>

  <f7-page name="anunciar-perdido">
    <f7-navbar title="Anuncio de mascota perdida" back-link="Volver"></f7-navbar>

    <CrearAnuncio tipo-anuncio="PERDIDO"/>

  </f7-page>

</template>

<script>
import CrearAnuncio from "../components/crearAnuncio";

export default {
  name: "anunciar-perdido",
  components: {
    CrearAnuncio
  }
}

</script>

<style scoped>

</style>