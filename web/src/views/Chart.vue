<template>
  <div>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>图表</el-breadcrumb-item>
    </el-breadcrumb>
    <br />
    <!-- <el-empty description="待开发"></el-empty> -->
    <div id="main"></div>
  </div>
</template>

<script>
import * as echarts from 'echarts/core';
import { TooltipComponent, LegendComponent } from 'echarts/components';
import { PieChart } from 'echarts/charts';
import { LabelLayout } from 'echarts/features';
import { CanvasRenderer } from 'echarts/renderers';

echarts.use([
  TooltipComponent,
  LegendComponent,
  PieChart,
  CanvasRenderer,
  LabelLayout
]);
export default {
  name: "ChartView",
  created () {

  },
  mounted () {
    // 基于准备好的dom，初始化echarts实例
    var chartDom = document.getElementById("main");
    var myChart = echarts.init(chartDom);
    var option;

    option = {
      tooltip: {
        trigger: "item",
      },
      legend: {
        top: "5%",
        left: "center",
      },
      series: [
        {
          name: "Access From",
          type: "pie",
          radius: ["40%", "70%"],
          avoidLabelOverlap: false,
          itemStyle: {
            borderRadius: 10,
            borderColor: "#fff",
            borderWidth: 2,
          },
          label: {
            show: false,
            position: "center",
          },
          emphasis: {
            label: {
              show: true,
              fontSize: 40,
              fontWeight: "bold",
            },
          },
          labelLine: {
            show: false,
          },
          data: [
            { value: 12, name: "男" },
            { value: 5, name: "女" }
          ],
        },
      ],
    };

    option && myChart.setOption(option);
  },
};
</script>

<style>
#main,
html,
body {
  width: 100%;
}
#main {
  height: 600px;
}
</style>