<template>
  <f7-page name="home">
    <!-- Top Navbar -->
    <f7-navbar>
      <f7-nav-title class="enc-logo">
        <img alt="Logo" src="/static/icons/128x128.png" width="20"/> <strong>Encontralo</strong>.com.ar
      </f7-nav-title>
    </f7-navbar>

    <!-- Page content-->
    <f7-swiper>
      <f7-swiper-slide class="enc-slide">
        El buscador Nº1 de mascotas perdidas
        <f7-row class="enc-col-center-content hide-">
          <f7-col width="90" xsmall="90" small="60" medium="33" xlarge="20" class="enc-main-button">
            <f7-button large fill raised href="/anunciar-perdido" class="enc-cta">Anunciar perdido</f7-button>
          </f7-col>
        </f7-row>
      </f7-swiper-slide>
    </f7-swiper>

    <f7-block>
      <f7-row class="enc-col-center-content">
        <f7-col width="90" xsmall="90" small="60" medium="33" xlarge="20" class="enc-main-button">
          <f7-button large fill raised href="/anunciar-perdido" class="enc-cta">Anunciar perdido</f7-button>
        </f7-col>
        <f7-col width="90" xsmall="90" small="60" medium="33" xlarge="20" class="enc-main-button">
          <f7-button large fill raised href="/anunciar-encontrado">Anunciar encontrado</f7-button>
        </f7-col>
      </f7-row>
    </f7-block>

    <f7-block>
      <f7-row class="enc-col-center-content">
        <f7-col width="90" xsmall="90" small="60" medium="33" xlarge="20" class="enc-main-button">
          <f7-button large href="/buscar" icon-f7="search">Buscar</f7-button>
        </f7-col>
      </f7-row>
    </f7-block>

    <f7-block-title>Anuncios recientes</f7-block-title>
    <f7-row>
      <f7-col v-for="anuncio in anunciosHome" width="100" medium="33" xlarge="20">
        <AnuncioResumidoCard :anuncio-resumido="anuncio"></AnuncioResumidoCard>
      </f7-col>
    </f7-row>

    <f7-block :hidden="anunciosHome.length === 0">
      <f7-row class="enc-col-center-content">
        <f7-col width="90" xsmall="90" small="60" medium="33" xlarge="20" class="enc-main-button">
          <f7-button large href="/buscar" icon-f7="search">Buscar</f7-button>
        </f7-col>
      </f7-row>
    </f7-block>

  </f7-page>
</template>

<style>

.enc-main-button {
  margin: 12px;
}

.enc-slide {
  text-align: center;
  font-size: 2em;
  line-height: 350px;
  box-sizing: border-box;
  border: 1px solid #ccc;
  color: white;
  text-shadow: 0 0 3px #000;
  background-image: url("/src/static/img/slide_1.jpg");
  background-position: center;
}

.enc-slide .enc-cta {
  display: none;
}

@media only screen and (max-width: 568px) {
  .enc-slide {
    line-height: 230px;
    background-image: url("/src/static/img/slide_1_mobile.jpg");
    background-size: cover;
    height: calc(100vh - var(--f7-navbar-height));
  }

  .enc-cta {
    display: none;
  }

  .enc-slide .enc-cta {
    display: block;
  }
}

@media only screen and (min-width: 1200px) {
  .enc-slide {
    background-size: cover;
  }
}

</style>
<script>
import AnuncioResumidoCard from "../components/anuncioResumidoCard";
import anuncio from "../js/requests/anuncio";

export default {
  name: 'home',
  displayName: 'Inicio',
  components: {AnuncioResumidoCard},
  data() {
    return {
      anunciosHome: []
    }
  },
  mounted() {
    const self = this;
    anuncio.obtenerAnunciosHome()
        .then(anunciosResponse => {
          console.log(anunciosResponse);
          self.anunciosHome = anunciosResponse.data.content.map((val) => {
            if (val.fotoPrincipal) {
              val.fotoPrincipal.url = process.env.BACK_URL + val.fotoPrincipal.url
            }
            return val
          });
        })
  },

}
</script>