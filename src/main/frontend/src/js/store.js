import {createStore} from 'framework7/lite';
import RequestsSeleccionables from './requests/seleccionables'

const store = createStore({
    state: {
        seleccionables: [],
        cargando: false
    },
    actions: {
        getSeleccionables({state}) {
            state.cargando = true
            RequestsSeleccionables.getSeleccionables
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
        seleccionables({state}) {
            return state.seleccionables;
        }
    }
})
export default store;
