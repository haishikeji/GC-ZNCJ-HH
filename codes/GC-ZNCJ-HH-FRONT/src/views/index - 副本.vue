<template>
	<div class="leftBg">
		<el-container>
			<el-header style="height:15vh">
				<div class="left-top-div">
					<span class="left-top-title">设备编号</span>
					<span class="left-top-title2">设备</span>
				</div>
				<div class="left-top-div2">
					<span class="left-top-title3">{{ deviceCode }}</span>
					<span class="left-top-title4">{{ deviceName }}</span>
				</div>
			</el-header>

			<el-main style="height:80vh">
				<div class="left-top-main">
					<el-container style="height:100%">
						<el-aside style="width: 28%;background-color: #3566dd;">
							<!-- <div v-for="site in sites"> -->
							<div v-for="site in sites">
								<div class="left-sc" @click="productionDate({ site })" >
									<el-container>
										<el-aside width="9%" style="height:19vh">
											<div :class="'left-left vertical-text '+ 'left-list-bg'+site.status">
												<span v-if="site.status === 0"  class="left-list-title">未开始</span>
												<span v-if="site.status === 1"  class="left-list-title">生产中</span>
												<span v-if="site.status === 2"  class="left-list-title">暂停生产</span>
												<span v-if="site.status === 3"  class="left-list-title">生产完成</span>
											</div>
										</el-aside>
										<el-main width="361px" style="height:19vh;padding:0">
											<div :class="'left-right-sc '+(index!=0 && site.id===index?'left-list-selected':'left-list-unselected') " >
												<el-row class="left-right-body">
													<el-col :span="23" class="left-body-title-left a">
														{{ site.typeName }}（
														<sapn style="font-weight: bold;" class="b">{{ site.level }}</sapn>
														）
													</el-col>
													<el-col :span="23" class="left-body-title-left g" >{{ site.productCode }}</el-col>
													<el-col :span="10" class="left-body-title-left a">计划产量</el-col>
													<el-col :span="10" class="left-body-title-right b" style="font-weight: bold;">
														{{ site.planOrderQuantity }}
														<sapn class="u">{{ site.productUnit }}</sapn>
													</el-col>
												</el-row>
											</div>
										</el-main>
										<div class="sanjiao" v-if="site.id===index"></div>
									</el-container>
								</div>
							</div>
						</el-aside>
						<el-main style="width: 70%;">
							<div class="right-div">
								<el-header>
									<div class="header-div"><span class="right-top-title">排查计划</span></div>
									<el-row class="right-body">
										<el-col :span="3" class="right-body-title right-body-title-left">品号：</el-col>
										<el-col :span="9" class="right-body-value" v-text="prList.productCode"></el-col>
										<el-col :span="5" class="right-body-title right-body-title-right">计划投料：</el-col>
										<el-col :span="7" class="right-body-value" v-text="prList.planIntoNum"></el-col>
									</el-row>
									<el-divider class="under-line"></el-divider>
									<el-row class="right-body">
										<el-col :span="3" class="right-body-title right-body-title-left">品名：</el-col>
										<el-col :span="9" class="right-body-value" v-text="prList.productName"></el-col>
										<el-col :span="5" class="right-body-title right-body-title-right">计划工时：</el-col>
										<el-col :span="7" class="right-body-value" v-text="prList.planTime"><sapn>小时</sapn></el-col>
									</el-row>
									<el-divider class="under-line"></el-divider>
									<el-row class="right-body">
										<el-col :span="3" class="right-body-title right-body-title-left">规格：</el-col>
										<el-col :span="9" class="right-body-value" v-text="prList.productSpec"></el-col>
										<el-col :span="5" class="right-body-title right-body-title-right">计划人工：</el-col>
										<el-col :span="7" class="right-body-value" v-text="prList.planPerson"><span>人</span></el-col>
									</el-row>
									<el-divider class="under-line"></el-divider>
									<el-row class="right-body">
										<el-col :span="5" class="right-body-title right-body-title-left">上次工单余量：</el-col>
										<el-col :span="7" class="right-body-value" v-text="prList.lastOrderQuantity"></el-col>
										<el-col :span="5" class="right-body-title right-body-title-right">本次工单余量：</el-col>
										<el-col :span="7" class="right-body-value" v-text="prList.thisOrderQuantity"></el-col>
									</el-row>
									<el-divider class="under-line"></el-divider>
									<el-row class="right-body">
										<el-col :span="3" class="right-body-title right-body-title-left">备注：</el-col>
										<el-col :span="21" class="right-body-value" v-text="prList.remark"></el-col>
									</el-row>
									<el-divider class="under-line"></el-divider>
									<div class="header-div"><span class="right-top-title">实时生产</span></div>
									<el-row class="right-body">
										<el-col :span="5" class="right-body-title right-body-title-left">实时产量：</el-col>
										<el-col :span="7" class="right-body-value" v-text="prList.produceNum"></el-col>
										<el-col :span="5" class="right-body-title right-body-title-right">完成率：</el-col>
										<el-col :span="7" class="right-body-value" v-text="prList.completeRate"></el-col>
									</el-row>
									<el-divider class="under-line"></el-divider>
									<el-row class="right-body">
										<el-col :span="5" class="right-body-title right-body-title-left">实际工时：</el-col>
										<!-- <el-col :span="7" class="right-body-value"><el-link icon="el-icon-edit" class="el-link-dj">点击输入</el-link></el-col> -->
										<el-col :span="7" class="right-body-value"  v-if="prList.status === 3 " v-text="prList.personTime">
										</el-col>
										<el-col :span="7" class="right-body-value"  v-else-if="prList.status === 0 || prList.status ===1 || prList.status ===2">
											<el-input v-model="input1"  placeholder="点击输入" style="border:none;"  class="el-link-dj">
												
											</el-input>
										</el-col>
										<el-col :span="5" class="right-body-title right-body-title-right">实际机时：</el-col>
										<el-col :span="7" class="right-body-value" v-text="prList.machTime"></el-col>
									</el-row>
									<el-divider class="under-line"></el-divider>
									<el-row class="right-body">
										<el-col :span="5" class="right-body-title right-body-title-left">实际人工：</el-col>
										<el-col :span="7" class="right-body-value" v-if="prList.status === 3 " v-text="prList.personNum">
											
										</el-col>
										<el-col :span="7" class="right-body-value" v-else-if="prList.status === 0 || prList.status ===1 || prList.status ===2">
											<el-input v-model="input2"  placeholder="点击输入" class="el-link-dj"></el-input>
										</el-col>
										<el-col :span="5" class="right-body-title right-body-title-right">实际投料：</el-col>
										<el-col :span="7" class="right-body-value" v-text="prList.additionNum"></el-col>
									</el-row>
									<el-divider class="under-line"></el-divider>
									<div class="header-div"><span class="right-top-title">不良品数</span></div>
									<el-row class="right-body">
										<el-col :span="4" class="right-body-title right-body-title-left">盒数：</el-col>
										<el-col :span="4" class="right-body-value" v-if="prList.status === 3 "  v-text="prList.badBoxNum">
											
										</el-col>
										<el-col :span="4" class="right-body-value" v-else-if="prList.status === 0 || prList.status ===1 || prList.status ===2">
											<el-input-number style="width:130px;" v-model="num" @change="handleChange" :min="500" :max="100000" label="" class="input-number"></el-input-number>
										</el-col>
										<el-col :span="4" class="right-body-title right-body-title-right">牙线棒支数：</el-col>
										<el-col :span="4" class="right-body-value" v-if="prList.status === 3 " v-text="prList.badBranchNum">
											
										</el-col>
										<el-col :span="4" class="right-body-value" v-else-if="prList.status === 0 || prList.status ===1 || prList.status ===2">
											<el-input v-model="input3"  placeholder="点击输入" class="el-link-dj"></el-input>
										</el-col>
										<el-col :span="4" class="right-body-title right-body-title-right">不良品率：</el-col>
										<el-col :span="4" class="right-body-value" v-text="prList.badRate"></el-col>
									</el-row>
									<el-divider class="under-line"></el-divider>
									<el-row :gutter="20">
										<el-col :span="12" :offset="6">
											<!-- <el-button style="font-size: 3rem;" type="info" :size="medium" @click="productStatue(1,prList.jhId)" v-if="prList.status===0 || prList.status===2">开始生产</el-button>
											<el-button style="font-size: 3rem;" type="danger" :size="medium" @click="productStatue(2,prList.jhId)" v-if="prList.status===1">暂停生产</el-button>				
											<el-button style="font-size: 3rem;" type="success" :size="medium" @click="productStatue(3,prList.jhId)" v-if="prList.status===1 ">生产完成</el-button>	 -->
											<el-button	style="font-size: 3rem;" type="info" :size="medium"	@click="productStatue(1)" v-if="prList.status === 0 || prList.status === 2">
												开始生产
											</el-button>
											<el-button style="font-size: 3rem;" type="danger" :size="medium" @click="productStatue(2)" v-if="prList.status === 1 ">
												暂停生产
											</el-button>
											<el-button style="font-size: 3rem;" type="success" :size="medium" @click="productStatue(3)" v-if="prList.status === 2 || prList.status === 1">
												生产完成
											</el-button>
										</el-col>
									</el-row>
									<!-- <div class="msg" style="width: 70%;height: 150%;">
										<el-row :gutter="20">
											<el-col :span="12" :offset="6">
												<el-button style="font-size: 6rem;margin-left:10px;background-color: #d8a842" type="info" :size="medium">三盒</el-button>
												<el-button style="font-size: 6rem;margin-left:10px;background-color: #d8a842" type="danger" :size="medium">四盒</el-button>
												
											</el-col>
										</el-row>
									</div> -->
								</el-header>
							</div>
						</el-main>
					</el-container>
				</div>
			</el-main>
		</el-container>
		<el-dialog title="投料盒数选择" :visible.sync="showDialog">
			<div class="msg" style="width: 100%;height: 20rem;">
				<div style="float: left;width: 45%;text-align: right;">
					<el-button style="font-size: 10rem;background-color: #d8a842;" type="info" :size="medium" @click="heNumber(3)">三盒</el-button>
				</div>
				<div style="float: left;width: 45%;text-align: left;margin-left: calc(5% - 1px);">
					<el-button style="font-size: 10rem;background-color: #d8a842;" type="danger" :size="medium" @click="heNumber(4)">四盒</el-button>
				</div>
			</div>
		</el-dialog>
		<el-dialog title="警告" :visible.sync="showDialog1">
			<div class="msg" style="width: 60%;height: 10rem;">
				<div style="float: left;width: 100%;text-align: right;">
					<div style="font-size: 3rem;color:red">
						请先选择盒数！
					</div>
					<div>
						<el-button style="font-size: 2rem;background-color: blue;margin-top:10%" type="info" :size="medium" @click="sure()">确定</el-button>
					</div>
					
				</div>
			</div>
		</el-dialog>
	</div>
	<!-- <div class="right-top-div">
			<div class="right-top-div-body">
				<svg-bar :value="设备状态.svg1" :options="options1"></svg-bar>
			</div>
			<div class="right-top-div-title" style="color: #01AEA6;">设备运行率</div>
		</div>
		<div class="right-top-div">
			<div class="right-top-div-body">
				<svg-bar :value="设备状态.svg2" :options="options2"></svg-bar>
			</div>
			<div class="right-top-div-title" style="color: #F6ED33;">设备待机率</div>
		</div>
		<div class="right-top-div">
			<div class="right-top-div-body">
				<svg-bar :value="设备状态.svg3" :options="options3"></svg-bar>
			</div>
			<div class="right-top-div-title" style="color: #FD397A;">设备故障率</div>
		</div>
		<div style="height: 22vh;">
			<div style="margin-top: 8vh;margin-left: 3rem;">
				<div class="left-top-div">
					<span class="left-top-title">当天设备总体使用效率</span>
					<span style="font-family: lcdd;float: right;">
						<span style="font-size: 5rem;color: #0FFF49;" v-text="车间信息.效率">100</span>
						<span style="font-size: 3rem;">%</span>
					</span>
				</div>
				<div class="left-top-div">
					<span class="left-top-title">当天生产数量总和</span>
					<span style="font-family: lcdd;float: right;">
						<span style="font-size: 5rem;" v-text="车间信息.总和">1242134</span>
					</span>
				</div>
			</div>
		</div> -->
</template>
<script>
//下方右图
export default {
	name: 'DataView',
	components: {},
	productCode: 'q1',
	data() {
		return {
			input1: '',
			input2: '',
			input3: '',
			num: 500,
			index:0,
			showDialog: false,
			showDialog1: false,
			deviceCode: '',
			deviceName: '',
			device: { deviceCode: '', deviceName: '' },
			ip: '',
			sites: [{ statue: '0', typeName: '白班' }, { statue: '2', typeName: '白班' }, { statue: '3', typeName: '夜班' }],
			prList: {},
			options1: {
				radius: 60, //大小
				duration: 2, //变化时间长度
				circleWidth: 10, //圆环宽度
				pathColors: ['#0E2D5B', '#00B3A7'], //圆环 底色和 上面的颜色
				circleLineCap: 'round', //进度条 形状  圆环 或者矩形
				text: function(value) {
					return '<div style="color:#00B3A7">' + value + '%</div>';
				}
			},
			options2: {
				radius: 60, //大小
				duration: 2, //变化时间长度
				circleWidth: 10, //圆环宽度
				pathColors: ['#0E2D5B', '#F9ED35'], //圆环 底色和 上面的颜色
				circleLineCap: 'round', //进度条 形状  圆环 或者矩形
				text: function(value) {
					return '<div style="color:#F9ED35">' + value + '%</div>';
				}
			},
			options3: {
				radius: 60, //大小
				duration: 2, //变化时间长度
				circleWidth: 10, //圆环宽度
				pathColors: ['#0E2D5B', '#FA5657'], //圆环 底色和 上面的颜色
				circleLineCap: 'round', //进度条 形状  圆环 或者矩形
				text: function(value) {
					return '<div style="color:#FA5657">' + value + '%</div>';
				}
			},
			loading: true
		};
	},
	methods: {
		productStatue: function(statue) {
			
			let that = this;
			let ip = that.ip;	
			let unit = that.prList.unit;
			let type = that.prList.sbType;
			if(statue === 3 || statue === 2){
				//暂停或者结束生产的时候更新页面输入的数据
				this.$http.get('api/produce/isDoding?sbIp=' + ip).then(resp => {
					let data = resp.data;
					if (data.status === false) {
						this.$http.post('api/produce/update', {
								jhId: that.prList.jhId,
								status: statue,
								personTime:that.input1,
								personNum:that.input2,
								badBranchNum:that.input3,
								badBoxNum:that.num
								
							})
							.then(function(response) {
								console.log(response);
								that.ready();
								that.getInfo();
								that.input1='';
								that.input2='';
								that.input3='';
								
							})
							.catch(function(error) {
								console.log(error);
							});
					}
				});
			}else if(statue === 1){
				//开始生产的时候如果是三四盒套膜机要验证是否选择套膜机盒数
				//不是三四盒套膜机
				
				//是三四盒套膜机sbType==1  0为不是，1为是
				if(type ===1){
					if(unit>0){
						this.$http.get('api/produce/isDoding?sbIp=' + ip).then(resp => {
							let data = resp.data;
							if (data.status === false) {
								this.$http.post('api/produce/update', {
										jhId: that.prList.jhId,
										status: statue,
										unit:unit
									})
									.then(function(response) {
										console.log(response);
										that.ready();
										that.getInfo();						 
										
									})
									.catch(function(error) {
										console.log(error);
									});
							}
						});
					}else{
						this.showDialog1 = true;
					}
				}else{//否  不是三四盒套膜机
					this.$http.get('api/produce/isDoding?sbIp=' + ip).then(resp => {
						let data = resp.data;
						if (data.status === false) {
							this.$http.post('api/produce/update', {
									jhId: that.prList.jhId,
									status: statue
									
								})
								.then(function(response) {
									console.log(response);
									that.ready();
									that.getInfo();						 
									
								})
								.catch(function(error) {
									console.log(error);
								});
						}
					});
				}
				
			}
			
			/* this.$http.get('api/produce/isDoding?sbIp=' + ip).then(resp => {
				let data = resp.data;
				if (data.status === false) {
					this.$http.post('api/produce/update', {
							jhId: that.prList.jhId,
							status: statue
						})
						.then(function(response) {
							console.log(response);
							that.ready();
							that.getInfo();						 
							
						})
						.catch(function(error) {
							console.log(error);
						});
				}
			}); */ 
		},
		getInfo:function(){
			let that = this;
			this.$http.get('api/produce/getDetail?jhId=' + that.prList.jhId).then(resp => {
				let data = resp.data;
				if (data) {
					that.prList = data.detail;
				}
			});
		},
		heNumber: function(num) {
			let that = this;

			if (num) {
				that.prList.unit = num;
				this.showDialog = false;
			}
		},
		ready: function() {
			let that = this;
			this.$http.get('api/ipAddr/ip').then(resp => {
				let data = resp.data;
				if (data) {
					that.ip = data;
					this.production(that.ip);
				}
			});
		},
		productionDate(site) {
			let that = this;
			if (site) {
				that.prList = site.site;
				var sbtype = site.site.sbType;
				if (sbtype === 1 && site.site.status == 0) {
					this.showDialog = true;
				}
				that.index=site.site.id;
			}
			
		},
		production(ip) {
			let that = this;
			this.$http.get('api/produce/list?ipAddress=' + ip).then(resp => {
				let data = resp.data;
				if (data) {
					that.sites = data;
					that.deviceCode = data[0].deviceCode;
					that.deviceName = data[0].deviceName;
				}
			});
		},
		cancelLoading() {
			setTimeout(() => {
				this.loading = false;
			}, 2000);
		},
		handleChange(value) {
		        console.log(value);
		    },
		sure:function(){
			let that = this;
			this.showDialog1 = false;
		},
	},
	mounted() {
		this.ready();
		this.cancelLoading();
		/* 	this.production();	 */
	},

};
</script>
<style lang="scss" scoped>
@import '../assets/scss/index.scss';
@import '../assets/scss/font.css';
.leftBg {
	background-image: url(../assets/backgroud.png);
	background-repeat: 'no-repeat';
	background-size: '100% 100%';
	height: 100vh;
}
.info-div {
	padding-top: 2vh;
	width: 50%;
	height: 10vh;
	float: left;
	text-align: center;
}
.left-top-div {
	margin: 50px 0px 0px 10px;
}
.left-top-title {
	font-size: 2rem;
	letter-spacing: 3px;
	color: #9facdc;
	margin: 0px 10px 0px 10px;
}

.left-top-title2 {
	font-size: 2rem;
	letter-spacing: 3px;
	color: #9facdc;
	margin: 0px 10px 0px 150px;
}
.left-top-div2 {
	margin: 22px 0px 0px 0px;
}
.left-top-title3 {
	font-size: 2.5rem;
	color: white;
	margin: 0px 10px 0px 10px;
}
.left-top-title4 {
	font-size: 2.5rem;
	color: white;
	margin: 0px 10px 0px 150px;
}
.left-top-main {
	border-radius: 5px;
	-moz-border-radius: 5px;
	background-color: white;
	height: 100%;
}
.left-div {
	border-radius: 5px;
	-moz-border-radius: 5px;
	width: 100%;
	background-color: #3566dd;
	height: 100%;
}
.right-div {
	width: 100%;
	height: 100%;
}
.header-div {
	font-weight: bold;
	width: 100%;
	background-color: #e2e5f4;
	height: 100%;
	margin-bottom: 2rem;
}

.right-top-title {
	font-size: 2rem;
	letter-spacing: 3px;
	color: black;
	line-height: 60px;
	padding-left: 20px;
}
.right-body {
	font-size: 2rem;
}
.right-body-title {
	color: #9facdc;
	font-weight: bold;
}
.right-body-value {
	min-height: 1px;
	color: black;
	line-height: 40px;
}
.right-body-title-left {
	padding-left: 2em;
	text-align: left;
	line-height: 40px;
}
.right-body-title-right {
	text-align: right;
	line-height: 40px;
}
.under-line {
	margin: 18px 0;
}
.el-link-dj {
	font-weight: bold;
	font-size: 2rem;
	color: #2b5dd9;
}
.el-input__inner{
	font-weight: bold;
	font-size: 2rem;
	color: #2b5dd9;
	border:none;
}
.el-input__inner::-webkit-input-placeholder{
	color: #2b5dd9;
}

.input-number {
	font-size: 2rem;
}
.left-sc {
	margin-left: 4rem;
	border-radius: 5px;
	-moz-border-radius: 5px;
	width: 100%;
	background-color: #3566dd;
	height: 30%;
	padding: 3em 2em 3em 2em;
	position: relative;
}
.left-zt {
	color: #fff;
	margin-left: 4rem;
	border-radius: 5px;
	-moz-border-radius: 5px;
	width: 100%;
	background-color: #3566dd;
	height: 30%;
	padding: 3em 2em 3em 2em;
}

/* .left-wks {
    color:#fff;
	margin-left: 4rem;
	border-radius: 5px;
	-moz-border-radius: 5px;
	width: 100%;
	background-color: #3566dd;
	height: 30%;
	padding: 3em 2em 3em 2em;
	position: relative;
} */
.left-font {
	font-size: 2rem;
}
.left-right-sc {
	background-repeat: 'no-repeat';
	background-size: '100% 100%';
	height: 100%;
	width: 82%;
}

.sanjiao {
	margin-top: 6.5rem;
	position: absolute;
	left: 80%;
	width: 0;
	height: 0;
	border-top: 20px solid transparent;
	border-bottom: 20px solid transparent;
	border-left: 20px solid #fff;
}
.left-body-title-left {
	padding-top: 1.5em;
	padding-left: 2em;
	text-align: left;
}
.left-right-body {
	font-size: 2rem;
	width: 100%;
	height: 100%;
}
.left-body-title-right {
	margin-left: 1rem;
	padding-top: 1.5em;
	font-weight: bold;
	text-align: right;
}

.vertical-text {
	-webkit-writing-mode: vertical-rl;
	writing-mode: vertical-rl;
}
.left-left {
	width: 100%;
	height: 100%;
	text-align: center;
	-webkit-border-top-left-radius: 4px;
	border-radius: 10px 0 0 10px;
}
.left-list-bg0{
	background-color: #908284;
}
.left-list-bg1{
	background-color: #08b1c0;
}
.left-list-bg2{
	background-color: #E35b6b;
}
.left-list-bg3{
	background-color: #1cbb5d;
}
.msg {
	width: 50%;
	height: 150%;
}
.left-list-selected{
	background-color: #fff;
}
.left-list-unselected{
	background-color: #2845a1;
	color: #fff;
}
.left-list-selected .g{
	color:#999;
}
.left-list-selected .a{
	color:#333;
}
.left-list-selected .b{
	color:blue;
}
.left-list-selected .u{
	color:#666;
}
.left-list-title{
	color: #fff;font-size: 26px;    line-height: 44px;letter-spacing: 8px;
}
</style>
