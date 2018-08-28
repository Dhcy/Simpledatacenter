function translations($translateProvider) {
	$translateProvider.translations('en', {
		ADMIN: 'Admin',
		'切换语言':'Language',
		'退出登陆': 'Loginout'
	});
	$translateProvider.translations('zh', {
		ADMIN: '管理员',
		'切换语言' : '切换语言',
		'退出登陆': '退出登陆'
	});
	$translateProvider.preferredLanguage('zh');
}