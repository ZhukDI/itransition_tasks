class User

	def initialize(options={}) #инициализация при создании	={} - по умолчанию пустой хэш
		@user_name = options[:user_name]
		@email = options[:email]
		@password = options[:password] #минимум 6 cbvdjkjd
		@first_name  = options[:first_name]
		@last_name = options[:last_name]
		
	end

	attr_reader :user_name, :email, :password, :first_name , :last_name	
	attr_writer :user_name, :email, :password, :first_name , :last_name

	def info
		puts @user_name + ' ' + @email + '@binka.me' + ' ' + @password + ' ' + @first_name + ' ' + @last_name
	end

end