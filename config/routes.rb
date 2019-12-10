Rails.application.routes.draw do

  get 'todos/index'
  post 'todos/create'
  get 'todos/update'

  root 'todos#index'

  # For details on the DSL available within this file, see https://guides.rubyonrails.org/routing.html
end
