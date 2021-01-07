<template lang="pug">

	v-expansion-panels.teste(multiple, v-model="panel")
		v-expansion-panel
			v-expansion-panel-header
				span {{titulo}}
			div
				v-divider
			v-expansion-panel-content
				slot

</template>

<script>

export default {

	name: 'ExpansivePanel',

	data: () => {
		return {
			panel: [0, 1]
		};
	},

	props: {
		titulo: {
			type: [String]
		}
	},
};

</script>

<style lang="less">

@import "../assets/css/variaveis.less";

.spacer {
	padding-bottom: 1%;
}

.v-expansion-panel-header {
	color:  @color-primary;
	cursor: pointer;
	padding: 5px 24px;

	span {
		font-size: 24px !important;
	}
}

.v-expansion-panel-content {
	.v-expansion-panel-content__wrap {
		padding: 0 24px 0px 24px !important;
	}
}

</style>