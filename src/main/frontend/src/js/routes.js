import HomePage from '../pages/home.vue';
import AnunciarEncontrado from '../pages/anunciarEncontrado.vue';
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
        path: '/anunciar-encontrado',
        component: AnunciarEncontrado
    },
    {
        path: '(.*)',
        component: NotFoundPage,
    },
];

export default routes;
