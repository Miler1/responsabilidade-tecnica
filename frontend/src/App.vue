<template lang='pug'>

#app
	v-app
		v-main.default
			MenuSuperior

			v-container
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

		.swal2-shown {
			font-family: Roboto, sans-serif;
			-webkit-font-smoothing: antialiased;
			-moz-osx-font-smoothing: grayscale;

			.swal2-header {
				align-items: baseline;
				border-bottom: 1px solid #ddd;
			}

			.title-modal-confirm {
				font-size: 18px;
				text-align: left;
			}

			.message-modal-confirm {
				font-size: 16px;
				margin-top: 20px;
				text-align: left;
			}

			.swal2-cancel {
				margin-right: 130px;
				color: #777777;
				border: 1px solid #E0E0E0 !important;
				outline: none;
			}

			.swal2-close {
				margin-top: 4px;
			}
		}
	}
}

.default{
	background-color: #E0E0E0;
}

.container {
	width: 85% !important;
}

</style>