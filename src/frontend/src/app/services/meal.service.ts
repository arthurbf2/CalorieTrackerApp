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

  async createMeal(meal_type: string) {
    return api.post<MealResponse>(`/meals`, {
      localDate: "2024-10-10",
      mealType: meal_type
    })
  }

  async addMealItem(food_id: string, quantity: number, meal_type: string) {
    let meal_id = this.getMealId(meal_type)
    if (!meal_id) {
        const new_meal = await this.createMeal(meal_type)
        meal_id = new_meal.data.id
      }
    await api.post(`/meals/${meal_id}/items`, {
      food_id: food_id,
      quantity: quantity
    })
  }

}
