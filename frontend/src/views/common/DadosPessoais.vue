<template lang="pug">

		v-card.pa-5(outlined, tile)
			h2.spacer {{titulo}}
			v-divider.spacer
			slot

</template>

<script>

export default {

	name: 'DadosPessoais',

	props: {
		pessoa: {
			type: [Object]
		}
	},
};

</script>

<style lang="less" scoped>

.spacer {
	padding-bottom: 1%;
}

</style>