require 'capybara'
# require 'capybara/rspec'
require 'faker'
require 'capybara/dsl'
require_relative "user"

Capybara.run_server = false
Capybara.current_driver = :selenium
# Capybara.app_host = 'http://www.google.com'

puts "write count"
# count = gets.to_i
count = ARGV[0].to_i
puts count


module MyCapybaraTest
  	class Test
    	include Capybara::DSL

		def test_registration(count)
			
			unregistered_users = []
			registered_users = []
			for i in 0...count

				user = User.new({ :user_name => Faker::Internet.user_name(5..15),
							:email => Faker::Lorem.characters(10), 
							:password => Faker::Internet.password(8, 12), 
							:first_name => Faker::Name.first_name, 
							:last_name => Faker::Name.last_name })	
				puts "user created"			

				visit 'https://temp-mail.ru'
				# change email
				click_link('click-to-change')
				# insert email in input
				fill_in(class: 'form-control', with: user.email)
				find_button('postbut').click
				sleep 2
				visit 'https://temp-mail.ru'
				email = find_field('mail').value
				puts "email created"

				visit 'http://dev.by/registration'
				fill_in('user_username', with: 'Q')#user.user_name
				fill_in('user_email', with: email)
				fill_in('user_password', with: user.password)
				fill_in('user_password_confirmation', with: user.password)
				fill_in('user_first_name', with: user.first_name)
				fill_in('user_last_name', with: user.last_name)
				check('user_agreement')
				find_button(class: 'submit').click
				# find_button(value: 'Зарегистрироваться').click						
				puts "form complited"


				# sleep 5
				begin
					visit 'https://temp-mail.ru'
					click_link(class: 'title-subject')
					click_on('подтвердить')
					puts "registration success"
					registered_users.push(user)
					sleep 5
				rescue Capybara::ElementNotFound => e
					puts "User not registred"
					unregistered_users.push(user)
					#i-=1
				end
				# expect(page).to have_content 'Регистрация'

				if i == (count - 1)
					puts "\nRegistered users (" + registered_users.length.to_s + "):"
					registered_users.each { |user| user.info }
					puts
					puts "Unregistered users (" + unregistered_users.length.to_s + "):"
					unregistered_users.each { |user| user.info }
				end
				puts ""
			end
		end
  	end
end



t = MyCapybaraTest::Test.new
t.test_registration(count)

