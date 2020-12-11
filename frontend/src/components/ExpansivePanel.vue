<template lang="pug">

	v-expansion-panels(multiple, v-model="panel", readonly=true)
		v-expansion-panel
			v-expansion-panel-header.expansion-header
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

.expansion-header {
	color:  rgb(4, 82, 4);
	cursor: default;
	font-size: 24px !important;
	height: 80px;
	padding: 0 20px;
	font-weight: bold;
	border-radius: 5px;
}

</style>