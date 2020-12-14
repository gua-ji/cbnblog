<template>
	<div class="about">
    <Header></Header>
		<el-row class="main" type="flex" justify="center">
			<el-col :span="16">
				<h5 class="title"><i class="el-icon-star-on"></i>关于博客</h5>
				<el-card shadow="always">
					<dl class="dl-blog">
						<dt>技术栈</dt>
						<dd>Vue、Element-ui、Springboot、MybatisPlus、Mysql</dd>
						<dt>源码地址</dt>
						<dd>XXX</dd>
					</dl>
				</el-card>
				<h5 class="title"><i class="el-icon-star-on"></i>给我留言</h5>
				<el-card shadow="always">
					<el-form label-position="left" :rules="rules" label-width="80px" ref="formLabelAlign" :model="formLabelAlign">
						<el-form-item :label="$t('about.yourName')" prop="nickName">
							<el-input v-model="formLabelAlign.nickName"></el-input>
						</el-form-item>
						<el-form-item :label="$t('about.email')" prop="email">
							<el-input v-model="formLabelAlign.email"></el-input>
						</el-form-item>
						<el-form-item :label="$t('about.content')" prop="content">
							<el-input type="textarea" v-model="formLabelAlign.content"></el-input>
						</el-form-item>
						<el-form-item>
							<el-button type="primary" @click="submitForm('formLabelAlign')">提交</el-button>
						</el-form-item>
					</el-form>
				</el-card>
			</el-col>
		</el-row>
	</div>
</template>

<script>
	export default {
		name: 'about',
		data() {
			return {
				formLabelAlign: {
					nickName: '',
					email: '',
					content: '',
				},
				rules: {
					nickName: [{
						required: true,
						message: this.$t('about.nameTip'),
						trigger: 'blur'
					}],
					email: [{
							required: true,
							message: this.$t('about.emailTip1'),
							trigger: 'blur'
						},
						{
							type: 'email',
							message: this.$t('about.emailTip2'),
							trigger: ['blur', 'change']
						}
					],
					content: [{
						required: true,
						message: this.$t('about.contentTip'),
						trigger: 'blur'
					}]
				}
			};
		},
		methods: {
			submitForm(formName) {
				this.$refs[formName].validate((valid) => {
					if(valid) {
					  this.$axios.post('/comment/add', this.formLabelAlign).then(res => {
            this.$alert('操作成功', '提示', {
              confirmButtonText: '确定',
              callback: action => {
                this.$router.go(0)
              }
            });
          })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
			}
		}
	}
</script>

<style scoped>
	.dl-blog dd {
		margin-left: 30px;
	}
	
	.dl-blog .icon {
		width: 20px;
		height: 20px;
	}
	
	.title {
		margin-top: 40px;
	}
	
	.statement {
		border-left: 3px solid #F56C6C;
		padding: 20px;
		background-color: #EBEEF5;
		margin-top: 20px;
	}
</style>