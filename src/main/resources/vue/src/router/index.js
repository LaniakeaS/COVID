import Vue from 'vue'
import Router from 'vue-router'
import mapW from '@/components/mapW'
import mapC from '@/components/mapC'
Vue.use(Router)

export default new Router({
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
