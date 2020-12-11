<template lang="pug">

	v-expansion-panels(multiple, v-model="panel")
		v-expansion-panel
			v-expansion-panel-header.expansion-header
				span {{titulo}}
			v-divider
			v-expansion-panel-content
				slot

</template>

<script>

export default {

	name: 'Panel',

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
	color:  #333;
	cursor: pointer;
	font-size: 20px !important;
	// height: 40px;
	padding: 0 20px;
	font-weight: bold;
	border-radius: 5px;
}

</style>