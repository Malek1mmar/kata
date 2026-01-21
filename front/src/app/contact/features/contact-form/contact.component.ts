import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { MessageService } from 'primeng/api';
import { ToastModule } from 'primeng/toast';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-contact',
  standalone: true,
  templateUrl: "./contact.component.html",
  styleUrls: ["./contact.component.scss"],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    ButtonModule,
    InputTextModule,
    InputTextareaModule,
    ToastModule
  ],
  providers: [MessageService]
})
export class ContactComponent {
  private fb = inject(FormBuilder);
  private messageService = inject(MessageService);

  public contactForm = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    message: ['', [Validators.required, Validators.maxLength(300)]]
  });

  onSubmit() {
    if (this.contactForm.valid) {
      this.messageService.add({
        severity: 'success',
        summary: 'Succès',
        detail: 'Demande de contact envoyée avec succès'
      });

      this.contactForm.reset();
    }
  }

  get messageLength(): number {
    return this.contactForm.get('message')?.value?.length ?? 0;
  }
}
