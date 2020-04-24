import Vue from 'vue/dist/vue.js'
import Router from 'vue-router'
import mapW from '@/components/mapW'
import mapC from '@/components/mapC'
Vue.use(Router)

export default new Router({   // Set link in the webpage
  routes: [
    {
      path: '/',
      name: 'mapC',
      component: mapC
    },
    {
      path: '/mapW',
      name: 'mapW',
      component: mapW
    }
  ]
})
