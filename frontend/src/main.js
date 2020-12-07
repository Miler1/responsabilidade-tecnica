import Vue from 'vue';
import App from './App.vue';
import vuetify from './plugins/vuetify';
import router from './router';
import store from './store';
import ApiService from './services/api.service';

Vue.config.productionTip = false;

ApiService.init().then(() => {
  new Vue({
    vuetify,
    router,
    store,
    render: h => h(App)
  }).$mount('#app');
});
