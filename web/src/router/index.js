import { createRouter, createWebHistory } from 'vue-router'
import PkindexView from '../views/pk/PkindexView'
import RanklistindexView from '../views/ranklist/RanklistindexView'
import RecordindexView from '../views/record/RecordindexView'
import UserbotsindexView from '../views/user/bots/UserbotsindexView'
import NotFound from '../views/error/NotFound'
import UserAccountLoginViewVue from '@/views/user/account/UserAccountLoginView.vue'
import UserAccountRegisterViewVue from '@/views/user/account/UserAccountRegisterView.vue'

const routes = [
  {
    path: "/",
    name: "home",
    redirect: "/pk/",
  },
  {
    path: "/pk/",
    name: "pk_index",
    component: PkindexView,
  },
  {
    path: "/ranklist/",
    name: "ranklist_index",
    component: RanklistindexView,
  },
  {
    path: "/record/",
    name: "record_index",
    component: RecordindexView,
  },
  {
    path: "/user/bots/",
    name: "user_bots_index",
    component: UserbotsindexView,
  },
  {
    path: "/404/",
    name: "404",
    component: NotFound,
  },
  {
    path: "/:catchAll(.*)",
    redirect: "/404/",
  },
  {
    path: "/user/account/login/",
    name: "user_account_login",
    component: UserAccountLoginViewVue,
  },
  {
    path: "/user/account/register/",
    name: "user_account_register",
    component: UserAccountRegisterViewVue,
  },

]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
