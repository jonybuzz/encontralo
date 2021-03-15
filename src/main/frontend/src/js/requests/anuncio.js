import req from './requests'

export default {
    crear(nuevoAnuncio) {
        return req.rest.post('/api/anuncios', nuevoAnuncio)
    },
    obtenerAnunciosHome() {
        return req.rest.get('/api/anuncios')
    }
}