import HomePage from '../pages/home.vue';
import AnunciarPerdido from '../pages/anunciarPerdido.vue';


import NotFoundPage from '../pages/404.vue';

var routes = [
    {
        path: '/',
        component: HomePage,
    },
    {
        path: '/anunciar-perdido',
        component: AnunciarPerdido
    },
    {
        path: '(.*)',
        component: NotFoundPage,
    },
];

export default routes;
