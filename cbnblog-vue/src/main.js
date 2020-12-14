import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Element from 'element-ui'
import axios from 'axios'
import i18n from './i18n/i18n'
import mavonEditor from 'mavon-editor'
import Header from './components/Header'

import "element-ui/lib/theme-chalk/index.css"
import 'mavon-editor/dist/css/index.css'
import './assets/style.css'
import "./axios"
import "./permission"

Vue.use(Element)
Vue.use(mavonEditor)
Vue.component("Header",Header)

Vue.config.productionTip = false
Vue.prototype.$axios = axios

new Vue({
  router,
  store,
  i18n,
  render: h => h(App)
}).$mount('#app')
