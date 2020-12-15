<template lang="pug">

	v-expansion-panels(multiple, v-model="panel")
		v-expansion-panel
			v-expansion-panel-header
				span {{titulo}}
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

<style lang="less" scoped>

.spacer {
	padding-bottom: 1%;
}

.v-expansion-panel-header {
	color:  #777777;
	cursor: pointer;

	padding: 5px 24px;

	span {
		font-size: 24px !important;
	}
}

</style>