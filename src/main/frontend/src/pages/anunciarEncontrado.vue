<template>

  <f7-page name="anunciar-encontrado">
    <f7-navbar title="Anuncio de mascota encontrada" back-link="Volver"></f7-navbar>

    <CrearAnuncio tipo-anuncio="ENCONTRADO"/>

  </f7-page>

</template>

<script>
import CrearAnuncio from "../components/crearAnuncio";

export default {
  name: "anunciar-encontrado",
  components: {
    CrearAnuncio
  }
}

</script>

<style scoped>

</style>