import {createStore} from 'framework7/lite';
import Axios from 'axios'

const store = createStore({
    state: {
        seleccionables: [],
        cargando: false
    },
    actions: {
        getSeleccionables({state}) {
            state.cargando = true
            Axios.get('/api/seleccionables')
                .then(res => res.data)
                .then(seleccionables => {
                    state.seleccionables = seleccionables;
                    state.cargando = false
                })
        },
        cargando({state}, cargando) {
            state.cargando = cargando;
        },
    },
    getters: {
        colores({state}) {
            return state.seleccionables.colores;
        }
    }
})
export default store;
