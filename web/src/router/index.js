import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from '@/views/Layout'
import Login from '@/views/Login'
import Register from '@/views/Register'
import NotFound from '@/views/NotFound'
import Home from '@/views/Home'
import Dept from '@/views/Dept'
import Emp from '@/views/Emp'
import Chart from '@/views/Chart'

Vue.use(VueRouter)

const router = new VueRouter({
    mode: 'history',
    routes: [
        {
            path: '/',
            redirect: '/home'
        },
        {
            path: '/layout',
            component: Layout,
            children: [
                {
                    path: '/home',
                    component: Home
                },
                {
                    path: '/dept',
                    component: Dept
                },
                {
                    path: '/emp',
                    component: Emp
                },
                {
                    path: '/chart',
                    component: Chart
                }
            ]
        },
        {
            path: '/login',
            component: Login
        },
        {
            path: '/register',
            component: Register
        },
        {
            path: '*',
            component: NotFound
        }
    ]
})

export default router