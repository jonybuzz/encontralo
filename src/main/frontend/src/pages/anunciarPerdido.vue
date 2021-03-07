<template>

  <f7-page name="anunciar-perdido">
    <f7-navbar title="Anuncio de mascota perdida" back-link="Volver"></f7-navbar>

    <f7-row class="enc-col-center-content">
      <f7-col width="90" xsmall="90" small="80" medium="70" xlarge="50" class="enc-main-button">
        <f7-list inline-labels no-hairlines-md>
          <f7-list-item radio radio-icon="start" title="Perro" name="demo-radio-start" checked></f7-list-item>
          <f7-list-item radio radio-icon="start" title="Gato" name="demo-radio-start"></f7-list-item>
          <f7-list-input
              label="Sexo"
              type="select"
              placeholder="Seleccionar..."
          >
            <option value="" selected disabled>Seleccionar...</option>
            <option value="Male">Mestizo</option>
            <option value="Female">Bulldog</option>
          </f7-list-input>

          <f7-list-input
              label="Raza"
              type="select"
              placeholder="Seleccionar..."
          >
            <option value="" selected disabled>Seleccionar...</option>
            <option value="Male">Mestizo</option>
            <option value="Female">Bulldog</option>
          </f7-list-input>

          <f7-list-input
              label="Tamaño"
              type="select"
              placeholder="Seleccionar..."
          >
            <option value="" selected disabled>Seleccionar...</option>
            <option value="Chico">Chico</option>
            <option value="Grande">Grande</option>
          </f7-list-input>

          <f7-list-input
              label="Edad"
              type="select"
              placeholder="Seleccionar..."
          >
            <option value="" selected disabled>Seleccionar...</option>
            <option value="Chico">Chico</option>
            <option value="Grande">Grande</option>
          </f7-list-input>

          <f7-list-item title="Colores" smart-select>
            <select name="colores" multiple>
              <option value="Blanco">Blanco</option>
              <option value="pineapple">Negro</option>
              <option value="pear">Marrón</option>
              <option value="orange">Beige</option>
              <option value="melon">Naranja</option>
            </select>
          </f7-list-item>

          <f7-list-input
              label="Pelaje"
              type="select"
              placeholder="Seleccionar..."
          >
            <option value="" selected disabled>Seleccionar...</option>
            <option value="Chico">Chico</option>
            <option value="Grande">Grande</option>
          </f7-list-input>

          <f7-list-input
              label="¿Tiene collar?"
              type="select"
              placeholder="Seleccionar..."
          >
            <option value="" selected disabled>Seleccionar...</option>
            <option value="Chico">Sí</option>
            <option value="Grande">No</option>
          </f7-list-input>

          <f7-list-input
              label="Pelaje"
              type="select"
              placeholder="Seleccionar..."
          >
            <option value="" selected disabled>Seleccionar...</option>
            <option value="Chico">Chico</option>
            <option value="Grande">Grande</option>
          </f7-list-input>

          <f7-list-input
              label="Localidad"
              type="select"
              placeholder="Seleccionar..."
          >
            <option value="" selected disabled>Seleccionar...</option>
            <option value="Chico">Chico</option>
            <option value="Grande">Grande</option>
          </f7-list-input>

          <f7-list-input
              label="Comentarios"
              type="textarea"
              placeholder=""
          >
          </f7-list-input>

        </f7-list>
      </f7-col>
    </f7-row>

    <f7-row class="enc-col-center-content">
      <f7-col width="90" xsmall="90" small="60" medium="33" xlarge="20" class="enc-main-button">
        <f7-button large fill raised href="/anunciar-perdido">Anunciar</f7-button>
      </f7-col>
    </f7-row>

  </f7-page>

</template>

<script>
export default {
  name: "anunciarPerdido.vue"
}
</script>

<style scoped>

</style>