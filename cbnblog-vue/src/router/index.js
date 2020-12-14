import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Blogs from '../views/Blogs.vue'
import BlogEdit from '../views/BlogEdit.vue'
import BlogDetail from '../views/BlogDetail.vue'
import Archive from "../views/Archive";
import Admin from "../views/Admin";
import TagBlog from "../views/TagBlog";
import about from "../views/about"
import gc from "@/views/gc";
import comments from "@/views/comments";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Index',
    redirect: {name: "Blogs"}
  },
  {
    path: '/blogs',
    name: 'Blogs',
    component: Blogs
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/blog/:blogId',
    name: 'BlogDetail',
    component: BlogDetail
  },
  {
    path: '/blog/:blogId/edit',
    name: 'BlogEdit',
    component: BlogEdit,
    meta: {
      requireAuth: true
    }
  },
  {
    path: '/archive',
    name: 'Archive',
    component: Archive,
  },
  {
    path: '/admin',
    name: 'Admin',
    component: Admin,
    meta: {
      requireAuth: true
    }
  },
  {
    path: '/admin/gc',
    name: 'gc',
    component: gc,
    meta: {
      requireAuth: true
    }
  }, {
    path: '/admin/comments',
    name: 'comments',
    component: comments,
    meta: {
      requireAuth: true
    }
  },
  {
    path: '/admin/blogadd',
    name: 'BlogAdd',
    component: BlogEdit,
    meta: {
      requireAuth: true
    }
  },
  {
    path: '/tagblog',
    name: 'TagBlog',
    component: TagBlog,
  },
  {
    path: '/about',
    name: 'about',
    component: about,
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
