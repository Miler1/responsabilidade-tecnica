<template lang='pug'>

#app
	v-app
		v-main
			MenuSuperior
			router-view

</template>

<script>
import MenuSuperior from '@/components/MenuSuperior.vue';

export default {

	name: "App",
	components: {
		MenuSuperior
	},

};

</script>

<style lang="less">
html {
  overflow-y: auto !important;

  body {
    margin: 0;

    #app {
      font-family: Roboto, sans-serif;
      -webkit-font-smoothing: antialiased;
      -moz-osx-font-smoothing: grayscale;
    }
  }
}
</style>