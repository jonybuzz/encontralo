/**
 * Configuracion y estructura global del sitio
 * @author jonybuzz
 */
<template>
  <f7-app v-bind="f7params" >

  <!-- Your main view, should have "view-main" class -->
  <f7-view main class="safe-areas" url="/"></f7-view>

  </f7-app>
</template>
<script>
  import { ref, onMounted } from 'vue';
  import { f7, f7ready } from 'framework7-vue';


  import routes from '../js/routes.js';
  import store from '../js/store';

  import moment from 'moment';
  moment.locale('es');

  export default {
    setup() {

      // Framework7 Parameters
      const f7params = {
        name: 'Encontralo', // App name
        theme: 'auto', // Automatic theme detection

        // App store
        store: store,
        // App routes
        routes: routes,
        // Register service worker
        serviceWorker: {
          path: '/service-worker.js',
        },
        navbar: {
          iosCenterTitle: true,
          mdCenterTitle: true,
        },

      };
      // Login screen data
      const username = ref('');
      const password = ref('');

      const alertLoginData = () => {
        f7.dialog.alert('Username: ' + username.value + '<br>Password: ' + password.value, () => {
          f7.loginScreen.close();
        });
      }
      onMounted(() => {
        f7ready(() => {

          // Call F7 APIs here
          store.dispatch('getSeleccionables')
        });
      });

      return {
        f7params,
        username,
        password,
        alertLoginData,
      }
    }
  }
</script>