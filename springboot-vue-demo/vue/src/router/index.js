import { createRouter, createWebHistory } from 'vue-router'
import Layout from '../layout/Layout.vue'

const routes = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect: "/projects",
    children: [
      {
        path: 'user',
        name: 'User',
        component: () => import("@/views/pc/User"),
      },
      {
        path: '/book',
        name: 'Book',
        component: ()=>import("@/views/pc/Book")
      },
      {
        path: '/person',
        name: 'Person',
        component: ()=>import("@/views/pc/Person")
      },
      {
        path: '/news',
        name: 'News',
        component: ()=>import("@/views/pc/News")
      },
      {
        path: '/projects',
        name: 'Projects',
        component: ()=>import("@/views/pc/Projects")
      },{
        path: '/items',
        name: 'Items',
        component: ()=>import("@/views/pc/Items")
      },
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: ()=>import("@/views/pc/Login")
  },
  {
    path: '/register',
    name: 'Register',
    component: ()=>import("@/views/pc/Register")
  },{
    path: '/dictionary',
    name: 'Dictionary',
    component: ()=>import("@/views/mobile/Dictionary")
  },
  ,{
    path: '/upload',
    name: 'UploadFile',
    component: ()=>import("@/views/mobile/TransformICD10")
  },

]

const router = createRouter({
  history: createWebHistory(),
  //history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
