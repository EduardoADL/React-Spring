import axios from 'axios'

export const instance = axios.create({
    baseURL: 'http://172.18.0.1:8080'
})
