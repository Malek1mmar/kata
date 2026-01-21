import { Injectable, signal, computed } from '@angular/core';
import { Product } from '../../products/data-access/product.model';

@Injectable({ providedIn: 'root' })
export class CartService {
  private _cartItems = signal<Product[]>([]);

  // Liste des produits dans le panier
  public items = this._cartItems.asReadonly();

  // Nombre total (pour le badge)
  public count = computed(() => this._cartItems().length);

  addToCart(product: Product) {
    this._cartItems.update(items => [...items, product]);
  }

  removeFromCart(productId: number) {
    this._cartItems.update(items => items.filter(p => p.id !== productId));
  }
}
