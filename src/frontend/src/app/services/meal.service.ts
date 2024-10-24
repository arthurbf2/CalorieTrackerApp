import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import api from './api.service'
import { MealResponse } from '../types/meal-response.type';

@Injectable({
  providedIn: 'root'
})

export class MealService {

  constructor(private http: HttpClient) {}

  /*
  async getMeals(date: string) {
    try {
      //const response = await api.get(`/meals/43eda56f-2b53-4bf8-8254-78960d92608c`); // pegar detalhes da meal 
      const response = await api.get(`meals?date=2024-10-10`) // pegar meals do dia 
      //const response = await api.post(`meals/mealID/items`, {
      //  food_id "213123";
      //  quantity = 100;
      //})
      console.log(response.data)
      return response.data;
    } catch (error) {
      console.log("Error fetching meal: ", error)
    }
  } */

  async getMeals(date: string): Promise<MealResponse[]> {
    try{
      const response = await api.get<MealResponse[]>(`meals?date=${date}`)
      return response.data;
    }
    catch(error){
      console.log('Couldnt fetch meal');
      throw error;
    }
  }

  async addMealItem() {
    const response = await api.post(`/meals/43eda56f-2b53-4bf8-8254-78960d92608c/items`, {
      date: "2024-10-10",
      itemRequestDTO: {
        foodId: "1234",
        quantity: 123
      }
    });
    //return this.http.post(`${this.baseUrl}/users/${userId}/meals/${mealId}/items`, item);
  }

}
