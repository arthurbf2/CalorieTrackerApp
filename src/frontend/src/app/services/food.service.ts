import { Injectable } from '@angular/core';
import api from './api.service'
import { FoodResponse } from '../types/food-response.type';


@Injectable({
  providedIn: 'root'
})
export class FoodService {

  constructor() { }

  async getFoodList(name: string): Promise<FoodResponse[]> {
    try {
      const response = await api.get(`foods?name=${name}`)
      return response.data
    } catch(error) {
      console.log(error)
      throw error
    }
  }
}
