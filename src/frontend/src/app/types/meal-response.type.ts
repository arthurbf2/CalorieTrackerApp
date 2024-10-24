import { ItemResponse } from "./item-response.type"

export type MealResponse = {
    id: string,
    date: string,
    mealItems: ItemResponse[],
    mealType: string,
    totalCalories: number,
    totalFat: number,
    totalCarbs: number,
    totalProtein: number
}