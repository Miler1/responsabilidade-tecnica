<template lang="pug">

#text-field
	v-label {{labelOption}}
	v-text-field(
		v-model="modelText"
		color="#E0E0E0",
		:placeholder="placeholder",
		:id="id",
		:error-messages="errorMessages(modelText, true)",
		required,
		dense,
		outlined
	)

</template>

<script>
export default {
	name: 'TextField',

	props: {
		labelOption: {
			type: [String]
		},
		id: {
			type: [String]
		},
		placeholder: {
			type: [String]
		},
		errorMessages: {
			type: [Function]
		},
	},

	data: () => {
		return {
			modelText: null
		};
	},

	methods: {
		clearModel() {
			this.modelText = null;
		},

		setModel(valueModel) {
			this.modelText = valueModel;
		}
	}
};
</script>

<style lang="less" scoped>

#text-field {
	.v-label {
		color: #333;
		font-weight: bold;
		font-size: 16px;
	}

	span {
		font-weight: 400;
		font-size: 16px;
	}

}

</style>