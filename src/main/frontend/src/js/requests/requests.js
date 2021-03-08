import axios from 'axios'

export default {
    rest: axios.create({
        baseURL: process.env.BACK_URL,
        headers: {
            'Content-Type': 'application/json'
        },
        responseType: 'json',
    })
}