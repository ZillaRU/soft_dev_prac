import Vue from 'vue'
import Router from 'vue-router'
import context from '@/components/context'
import Home from '@/components/home'
import Person from '@/components/person'
import Detail from '@/components/detail/detail-context'

Vue.use(Router)

export default new Router({

  routes: [
    {
      path: '/',
      name: 'context',
      component: context
    },
    {
      path: '/:id',
      name: 'detail',
      component: context
    },
    {
      path:'/home/:id',
      component:Home,
      children:[
        {
          path: 'person',
          component: Person
        }
      ]
    },{
      path:'/detail/:id',
      name:'Detail',
      component:Detail
    },

  ]
})
