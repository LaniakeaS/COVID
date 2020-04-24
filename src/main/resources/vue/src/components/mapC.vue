<template>

	<div class="hello">

	<!-- Initialize echarts which need a box with a width and a height -->

		<div ref="mapbox" class="box">

		</div>

	</div>

</template>



<script>

	import echarts from 'echarts'
  import 'echarts-gl'
	import 'echarts/map/js/china.js'
	import jsonp from 'jsonp'



	const option = {
     backgroundColor: 'transparent',
		/*title: {
			text: 'Epidemic Map'
		},*/

		tooltip: {
	 //  The prompt message when the mouse moves in
	 //type
	    tigger: 'item',

	    // b: area name c: combined numerical a: series name
	    formatter: 'Area：{b}<br />Total confirmed: {c}'
	  },

		series: [{

			type: 'map3D', //Tell echarts to render the 3D map
			map: 'china',

			// shadow of map
      light: {
       main: {
           intensity: 3,
           shadow: true,
           alpha: 140,
          beta: 80
       },
       ambient: {
           intensity: 0
       }
   },
      groundPlane: {
         show: false
      },

		// Add a text label to the map that labels the coordinate axis indicator
			label: {
				show: false, //Show name of different provinces
				fontSize: 8,
			},

			// sliding location
			layoutCenter: ['50%', '40%'],
      layoutSize: 650,

			// Polygon pattern for map area
			itemStyle: {
				areaColor: '#fff' //The background color of the area
			},

      data: [{ name: '', value: '' }],

			//Controls the highlight style when the mouse slides
			emphasis: {
				itemStyle: {
					areaColor: '#c7fffd'
				}
			},

      //Scale of the current view
			zoom: 1,

		}],

		visualMap: {

	    // Set to segment type
	    type: 'piecewise',
	    show: true,

	    // Sets different bits

	    pieces: [

	      //Not specify Max, meaning Max is infinite
	      { min: 10000 },

	      { min: 1000, max: 9999 },

	      { min: 100, max: 999 },

	      { min: 10, max: 99 },

	      { min: 1, max: 9 },

	      { value: 0 }
	    ],

     // The color of the range
	    inRange: {
	      color: ['#fff', '#ffaa85', '#ff7b69', '#cc2929', '#8c0d0d', '#660208']
	    },

     // Properties of range item
	    itemWidth: 10,
	    itemHeight: 10,
	    bottom: 150,
	    left: 100
	  }
	};


	export default {

		name: 'Map',

		mounted() {

			this.getData();

			this.mychart = echarts.init(this.$refs.mapbox); //Get mapbox

			this.mychart.setOption(option);

		},

		methods: {
      // Get data from json file
			getData() {

				jsonp(

					'http://interface.sina.cn/news/wap/fymap2020_data.d.json?_=1580892522427', {}, (erros, data) => {

						// console.log(data);

						// console.log(data.data.list)

						var lists = data.data.list.map(item => {

							return {

								name: item.name,

								value: item.value

							}

						})

						console.log(lists)

						option.series[0].data = lists;

						this.mychart.setOption(option);

					})

			}

		}

	}

</script>



<style scoped>
  // Style of box which  contains echarts map
	.box{

		width: 1250px;

		height: 850px;

		margin: auto;

		/* border: 2px solid aquamarine; */

	}

</style>
