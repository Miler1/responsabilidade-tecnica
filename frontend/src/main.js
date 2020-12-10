import Vue from 'vue';
import App from './App.vue';
import vuetify from './plugins/vuetify';
import router from './router';
import store from './store';
import VueSimpleAlert from 'vue-simple-alert';
import ApiService from './services/api.service';

Vue.use(VueSimpleAlert);
Vue.config.productionTip = false;

ApiService.init().then(() => {
  new Vue({
    vuetify,
    router,
    store,
    render: h => h(App)
  }).$mount('#app');
});
