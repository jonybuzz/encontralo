<template>
  <f7-page name="home">
    <!-- Top Navbar -->
    <f7-navbar>
      <f7-nav-left>
        <f7-link
          icon-ios="f7:menu"
          icon-aurora="f7:menu"
          icon-md="material:menu"
          panel-open="left"
        ></f7-link>
      </f7-nav-left>
      <f7-nav-title>Encontralo.com.ar</f7-nav-title>
    </f7-navbar>
    <!-- Toolbar-->

    <!-- Page content-->
    <f7-block strong>
      <p>El buscador Nº1 de mascotas perdidas</p>
    </f7-block>

    <f7-row class="demo-col-center-content">
      <f7-col width="90" medium="40">
        <f7-button large fill raised href="/publicar">Publicar</f7-button>
      </f7-col>
    </f7-row>

    <f7-block-title>Algunos perdidos</f7-block-title>
    <f7-row>
      <f7-col width="100" medium="33" xlarge="20">
        <f7-card class="demo-card-header-pic">
          <f7-card-header
            class="no-border"
            valign="bottom"
            style="
              background-image: url(https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJe2jzHNs4Ex_L2_Yi6HtpXICEp3PYQjnsNg&usqp=CAU);
            "
            >Rocko</f7-card-header
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
            >Laila</f7-card-header
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
.demo-col-center-content {
  display: flex;
  align-items: center;
  justify-content: center;
}
.demo-card-header-pic .card-header {
  height: 140px;
  background-size: cover;
  background-position: center;
  color: #fff;
  text-shadow: 0 0 3px #000;
}
</style>