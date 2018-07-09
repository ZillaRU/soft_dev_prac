import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Home from '@/components/home'
import Person from '@/components/person'
import Detail from '@/components/detail/detail-context'

Vue.use(Router)

export default new Router({

  routes: [
    {
      path: '/index',
      name: 'HelloWorld',
      component: HelloWorld
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
