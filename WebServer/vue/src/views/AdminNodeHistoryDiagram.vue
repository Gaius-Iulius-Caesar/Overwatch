<template>
  <div id="box">

  </div>
</template>

<script>
import * as echarts from "echarts";
export default {
  name: "AdminNodeHistoryDiagram",
  mounted() {
    let chartDom = document.getElementById('box');
    let myChart = echarts.init(chartDom);
    let option;

    option = {
      xAxis: {
        type: 'category',
        data: ['1', '2', '3']
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [120, 200, 150],
          type: 'bar',
          showBackground: true,
          backgroundStyle: {
            color: 'rgba(180, 180, 180, 0.2)'
          }
        }
      ]
    };

    option && myChart.setOption(option);
  }
}

</script>

<style scoped>
#box{
  width: 100%;
  height: 50%;
}
</style>