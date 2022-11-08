<template>
  <div id="box">

  </div>
</template>

<script>
import * as echarts from "echarts";
import $ from "jquery";
export default {
  name: "AdminNodeRealTimeDiagram",
  data(){
    return{
      list:[],
      x_data:[],
      y_data:[]
    }
  },

  mounted() {
    let chartDom = document.getElementById('box');
    let myChart = echarts.init(chartDom);
    let option;

    this.request.get("http://localhost:8081/machine/getAllUpToDate").then(res => {
      if (res.code === '200') {
        this.list=$.parseJSON(res.msg)
        console.log(this.list)
        for(let i =0;i<this.list.length;i++){
          this.x_data.push(this.list[i].name)
        }
        for(let i =0;i<this.list.length;i++){
          this.y_data.push(this.list[i].avgLoad)
        }
        option && myChart.setOption(option)
      }
    })

    // console.log(this.list)
    option = {
      title: {
        text: '实时状态图'
      },
      xAxis: {
        name: 'name',
        type: 'category',
        data: this.x_data
      },
      yAxis: {
        name:'avgLoad',
        type: 'value'
      },
      series: [
        {
          data: this.y_data,
          type: 'bar',
          showBackground: true,
          backgroundStyle: {
            color: 'rgba(180, 180, 180, 0.2)'
          }
        }
      ]
    };

  },



}

</script>

<style scoped>
#box{
  width: 100%;
  height: 50%;
}

</style>