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
      <f7-swiper-slide class="enc-slide">El buscador Nº1 de mascotas perdidas</f7-swiper-slide>
    </f7-swiper>

    <f7-block>
      <f7-row class="enc-col-center-content">
        <f7-col width="90" xsmall="90" small="60" medium="33" xlarge="20" class="enc-main-button">
          <f7-button large fill raised href="/anunciar-perdido">Anunciar perdido</f7-button>
        </f7-col>
        <f7-col width="90" xsmall="90" small="60" medium="33" xlarge="20" class="enc-main-button">
          <f7-button large fill raised href="/anunciar-encontrado">Anunciar encontrado</f7-button>
        </f7-col>
      </f7-row>
    </f7-block>

    <f7-block>
      <f7-row class="enc-col-center-content">
        <f7-col width="90" xsmall="90" small="60" medium="33" xlarge="20" class="enc-main-button">
          <f7-button large raised href="/anunciar-perdido" icon-f7="search">Buscar</f7-button>
        </f7-col>
      </f7-row>
    </f7-block>

    <f7-block-title>Anuncios recientes</f7-block-title>
    <f7-row>
      <f7-col width="100" medium="33" xlarge="20">
        <f7-card class="demo-card-header-pic">
          <f7-card-header
              class="no-border"
              valign="bottom"
              style="
              background-image: url(https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJe2jzHNs4Ex_L2_Yi6HtpXICEp3PYQjnsNg&usqp=CAU);
            "
          >Rocko
          </f7-card-header
          >
          <f7-card-content>
            <p class="date">Publicado el 21 de enero, 2021</p>
            <p>Chihuaha, adulto mayor</p>
            <p>Quilmes, Bs. As.</p>
          </f7-card-content>
        </f7-card>
      </f7-col>
      <f7-col width="100" medium="33" xlarge="20">
        <f7-card class="demo-card-header-pic">
          <f7-card-header
              class="no-border"
              valign="bottom"
              style="
              background-image: url(https://www.elcomercio.com/files/article_main/uploads/2018/05/16/5afc9aa25b648.jpeg);
            "
          >Laila
          </f7-card-header
          >
          <f7-card-content>
            <p class="date">Publicado el 23 de enero, 2021</p>
            <p>Sharpei, cachorro</p>
            <p>Vicente López, Bs. As.</p>
          </f7-card-content>
        </f7-card>
      </f7-col>
      <f7-col width="100" medium="33" xlarge="20">
        <f7-card class="demo-card-header-pic">
          <f7-card-header
              class="no-border"
              valign="bottom"
              style="
              background-image: url(https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJe2jzHNs4Ex_L2_Yi6HtpXICEp3PYQjnsNg&usqp=CAU);
            "
          >Rocko
          </f7-card-header
          >
          <f7-card-content>
            <p class="date">Publicado el 21 de enero, 2021</p>
            <p>Chihuaha, adulto mayor</p>
            <p>Quilmes, Bs. As.</p>
          </f7-card-content>
        </f7-card>
      </f7-col>
      <f7-col width="100" medium="33" xlarge="20">
        <f7-card class="demo-card-header-pic">
          <f7-card-header
              class="no-border"
              valign="bottom"
              style="
              background-image: url(https://www.elcomercio.com/files/article_main/uploads/2018/05/16/5afc9aa25b648.jpeg);
            "
          >Laila
          </f7-card-header
          >
          <f7-card-content>
            <p class="date">Publicado el 23 de enero, 2021</p>
            <p>Sharpei, cachorro</p>
            <p>Vicente López, Bs. As.</p>
          </f7-card-content>
        </f7-card>
      </f7-col>
    </f7-row>
  </f7-page>
</template>

<style>
.demo-card-header-pic .card-header {
  height: 140px;
  background-size: cover;
  background-position: center;
  color: #fff;
  text-shadow: 0 0 3px #000;
}

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

@media only screen and (max-width: 568px) {
  .enc-slide {
    line-height: 280px;
    background-image: url("/src/static/img/slide_1_mobile.jpg");
    background-size: cover;
  }
}

</style>