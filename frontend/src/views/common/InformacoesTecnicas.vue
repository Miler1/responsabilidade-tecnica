<template lang="pug">

	div.mb-6
		ExpansivePanel(titulo = 'Contatos')
			span EM CONSTRUÇÃO

</template>

<script>
import DataUtils from '@/utils/dataUtils';
import ExpansivePanel from '@/components/ExpansivePanel';

export default {

	name: 'InformacoesTecnicas',

	components: {
		ExpansivePanel
	},

	data: () => ({
	}),

	props: {
		obj: {
			type: [Object]
		}
	},

	methods: {
		root() {

		}
	},
};

</script>

<style lang="less" scoped>

.spacer {
	padding-bottom: 1%;
}

</style>