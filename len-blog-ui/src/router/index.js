import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Home from '@/components/home'
import Person from '@/components/person'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
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
    }
  ]
})
