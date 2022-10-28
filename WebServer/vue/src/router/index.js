import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    {path: '/login', name: 'login', component: () => import(/* webpackChunkName: "about" */ '../views/Login.vue')},
    {
        path: '/register',
        name: 'register',
        component: () => import(/* webpackChunkName: "about" */ '../views/Register.vue')
    },
    {
        path: '/admin', name: 'Admin', component: () => import( '../views/Admin.vue'),
        // redirect: "/admin/managehome",
        children: [
            {path: 'adminconsole', name: 'AdminConsole', component: () => import( '../views/AdminConsole.vue')},
            {path: 'adminconfig', name: 'AdminConfig', component: () => import( '../views/AdminConfig.vue')},
            {
                path: 'adminnodestatus',
                name: 'AdminNodeStatus',
                component: () => import( '../views/AdminNodeStatus.vue')
            },
            {
                path: 'adminnoderealtimediagram',
                name: 'AdminNodeRealTimeDiagram',
                component: () => import( '../views/AdminNodeRealTimeDiagram.vue')
            },
            {
                path: 'adminnodehistorydiagram',
                name: 'AdminNodeHistoryDiagram',
                component: () => import( '../views/AdminNodeHistoryDiagram.vue')
            },
        ]
    },
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
