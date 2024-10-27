import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import api from './api.service'
import { MealResponse } from '../types/meal-response.type';

@Injectable({
  providedIn: 'root'
})

export class MealService {

  private meals: MealResponse[] = []

  constructor(private http: HttpClient) {}

  async getMeals(date: string): Promise<MealResponse[]> {
    try{
      const response = await api.get<MealResponse[]>(`meals?date=${date}`)
      this.meals = response.data
      return this.meals;
    }
    catch(error){
      console.log('Couldnt fetch meals');
      throw error;
    }
  }

  getMealId(meal_type: string): string {
    for(var meal of this.meals) {
      if(meal.mealType === meal_type) {
        return meal.id
      }
    }
    return ''
  }

  async addMealItem(food_id: string, quantity: number, meal_type: string) {
    const meal_id = this.getMealId(meal_type)
    if (!meal_id) {
      // criar a meal
      console.log("MEAL DOESNT EXIST")
    }
    /*
    const response = await api.post(`/meals/43eda56f-2b53-4bf8-8254-78960d92608c/items`, {
      date: "2024-10-10",
      itemRequestDTO: {
        foodId: "1234",
        quantity: 123
      }
    });
    */
    const response = await api.post(`/meals/${meal_id}/items`, {
      food_id: food_id,
      quantity: quantity
    })
    console.log("POSTED ADD MEAL ITEM: ", response.data)
  }

}
