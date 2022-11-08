<template>
  <div id="box">

  </div>
</template>

<script>
import * as echarts from "echarts";
import $ from "jquery";
export default {
  name: "AdminNodeHistoryDiagram",
  data(){
    return{
      list :[],
      client:[],
      ip:[],
      legend_data:[],
      timeStamp: [],

    }
  },
  mounted() {
    let chartDom = document.getElementById('box');
    let myChart = echarts.init(chartDom);
    let option;

    this.request.get("http://localhost:8081/machine/getAll").then(res => {
      if (res.code === '200') {
        this.list = $.parseJSON(res.msg)
        console.log(this.list)
        for(let i = 0;i<this.list.length;i++){
          let Name = this.list[i].name
          let avgload = this.list[i].avgLoad
          let timeStamp = this.list[i].timeStamp
          let ip = this.list[i].ip

          if(!this.legend_data.includes(Name)){//插入不重复的图例
            this.legend_data.push(Name)
          }

          if(!this.timeStamp.includes(timeStamp)){//插入不重复的时间戳
            this.timeStamp.push(timeStamp)
          }

          if(!this.ip.includes(ip)){//插入不重复的ip
            this.ip.push(ip)
            let client = {
              name : Name,
              type: 'line',
              stack: 'Total',
              data : [avgload]
            }
            this.client.push(client)
          }
          else{//遇到相同ip的
            let index = 0
            for(;index < this.client.length;index++){//找出索引
              if(this.client[index].name === Name){
                break;
              }
            }
            this.client[index].data.push(avgload)//往里面添加值
          }

        }
        option && myChart.setOption(option);
      }
    })

    option = {
      title: {
        text: '历史状态图'
      },
      tooltip: {
        trigger: 'axis'
      },
      legend: {
        data: this.legend_data
      },
      grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
      },
      toolbox: {
        feature: {
          saveAsImage: {}
        }
      },
      xAxis: {
        name: 'timeStamp',
        type: 'category',
        boundaryGap: false,
        data: this.timeStamp
      },
      yAxis: {
        name: 'avgLoad',
        type: 'value'
      },
      series: this.client
    };




  }
}

</script>

<style scoped>
#box{
  width: 100%;
  height: 50%;
}
</style>